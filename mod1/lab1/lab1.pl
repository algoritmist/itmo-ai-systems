% База знаний по игре Portal 2

is_woman(chell).  % главный герой в одиночном режиме
is_subject(chell). % Челл испытуемая в лаборатории

is_man(jonson). % ученый, основатель лаборатории
is_scientist(jonson).
is_dead(jonson).
is_woman(caroline). % асистентка Джонсона
is_dead(caroline).

is_human(H) :-
    is_man(H);
    is_woman(H).

is_lifeform(glados). % glados = generic lifeform and disk operating system
is_operting_system(glados).
has_intelligence(glados). % glados является ИИ
is_robot(glados). % у робота есть 'тело'

is_ai(wheatley). % модуль смягчения интеллекта
has_intelligence(wheatley).
is_idiot(wheatley).
is_robot(wheatley).

hates(glados, wheatley). % GLADOS не может терпеть Уитли

has_killed(glados, jonson).
has_killed(chell, glados). % В предыдущей части Челл убивает GLADOS
has_ressurected(wheatley, glados). % Уитли случайно воскрешает GLADOS во второй части

is_weapon(portal_gun). % портальная пушка позволяет перемещаться в пространстве

is_robot(turel). % роботы которые стреляют лазерами
has_intelligence(turel).
is_weapon(turel). 
is_weapon(laser).
has_laser(turel).

can_kick(chell, turel). % Можно пнуть турель, тогда она свалиться и будет 'Aaa...'

can_kill(turel, chell).
can_kill(water, chell).
can_kill(laser, chell).

is_key(usual_box). % для прохождения испытания необходимо открыть дверь с помощью ключа.
% Ключем является ящик, который необходимо поставить на определенное место

is_key(laser_box).
is_weapon(laser_box). % можно перенаправлять лазеры с помощью прозрачной стеклянной коробки
is_key(turel_box). % Уитли решил сделать испытуемых из турелей и интегрировал в них ящик

% Гели разработанные Джонсоном для исследования свойств порталов
is_gel(blue_moondust).
can_soar(chell, blue_moondust). % Синий гель позволяет высоко подпрыгивать, если на него наступить
is_gel(red_moondust).
can_speedup(chell, red_moondust). % Красный ускоряет передвижение
is_gel(gray_moondust).
can_open_portal(portal_gun, gray_moondust). % Серый позволяет открывать портал

is_panel(aerial_faith_plate). % Специальная панель, которая подбрасывает героя
can_soar(chell, aerial_faith_plate).

is_bridge(light_bridge). % Летающий световой мост по которому Челл может пройти
is_shield(light_bridge, laser). % Мост защищает от лазеров

% Воздущный мост, который может перемещать человека, роботов и предметы
is_bridge(air_bridge).
% Правило передвижение по мостам
can_move(air_bridge, S):-
    is_human(S);
    is_robot(S);
    is_key(S).


% Правило захвата объекта
can_capture(chell, portal_gun).
can_capture(H, O):-
    is_human(H),
    not(is_dead(H)),
    is_robot(O);
    is_key(O).

% Правило открытия порталов

can_shoot(portal_gun, gray_moondust). % Портальная пушка действует только на бетонные плиты, луну и серый гель состоящий из лунной пыли
can_shoot(portal_gun, concrete_slab).
can_shoot(portal_gun, moon).

can_shoot(turel, chell).

contains_key([]):- false.
contains_key([X:XS]):-
    is_key(X);
    contains_key(XS).

% Правило прохождения уровня

pass_level(HUMAN, ENVIRONMENT):-
    not(is_dead(HUMAN)),
    contains_key(ENVIRONMENT);
    open_portals(ENVIRONMENT);
    move_bridges(ENVIRONMENT);
    remove_turels(ENVIRONMENT);
    use_shields(ENVIRONMENT).

% Правило захвата объекта
