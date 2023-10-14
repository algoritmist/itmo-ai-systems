from enum import Enum
from pyswip import Prolog
import re

class Object(Enum):
    WEAPON = "weapon"
    ROBOT = "robot"

class UserInputFacts:
    name: str
    obj: Object

def parse_input(input_str: str):
    pattern = r"My name is (?P<name>\w+), I like (?P<objects>\w+): show me all of them I can take."
    facts = re.search(pattern, input_str.strip())

    obj = Object.WEAPON if facts.group("objects") == "weapons" else Object.ROBOT
    return UserInputFacts(name=facts.group("name"), obj=obj)

def build_query(user_input: UserInputFacts):
    prolog = Prolog()
    prolog.consult("../lab1/lab1.pl")
    facts = []

    name, obj = user_input.name.lower(), user_input.obj.value
    facts += [f"is_human({name})"]
    facts += [f"is_dead({name}) :- false"]
    facts += [f"can_capture_{obj}(H, O):- can_capture(H, O), is_{obj}(O)"]

    for fact in facts:
        print(fact)
        prolog.assertz(fact)

    query = f"can_capture_{obj}({name}, Object)."

    return prolog.query(query)

def get_response(query_result):
    recommendations = []
    for result in query_result:
        recommendation = result["Object"]
        recommendations.append(recommendation)
    return recommendations

def main():
    parsed_input = None
    while parsed_input is None:
        try:
            input_str = input()
            parsed_input = parse_input(input_str)
        except AttributeError:
            print("Wrong input format. Correct format is: My name is Slava, I like (robots/weapons): show me all of them I can take.")
            continue
    query_result = build_query(parsed_input)
    response = ", ".join(get_response(query_result))
    print(f"Ontology db answer: {response}")

if __name__ == "__main__":
    main()