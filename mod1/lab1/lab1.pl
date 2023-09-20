% База знаний по игре Portal 2

is_woman(chell).  % главный герой в одиночном режиме
is_woman(caroline). % асистентка Джонсона
is_man(jonson). % создатель испытаний на нижнем уровне

is_lifeform(glados). % glados = generic lifeform and disk operating system
is_operting_system(glados).
has_intelligence(glados).
has_body(glados).

is_ai(wheatley). % модуль смягчения интеллекта
has_intelligence(wheatley).
is_idiot(wheatley).

hates(glados, wheatley). % GLADOS не может терпеть Уитли

has_killed(chell, glados). % В предыдущей части Челл убивает GLADOS
has_ressurected(wheatley, glados). % Уитли случайно воскрешает GLADOS во второй части

is_weapon(portal_gun). % портальная пушка позволяет перемещаться в пространстве
can_use(chell, portal_gun).

is_weapon(turel). % роботы которые стреляют лазерами
has_laser(turel).

can_kill(turel, chell).
can_kill(water, chell).

is_key(usual_box). % для прохождения испытания необходимо открыть дверь с помощью ключа.
% Ключем является ящик, который необходимо поставить на определенное место

is_key(laser_box).
is_weapon(laser_box). % можно перенаправлять лазеры с помощью прозрачной стеклянной коробки
is_key(turel_box). % Уитли решил сделать испытуемых из турелей и интегрировал в них ящик

% Гели разработанные Джонсоном для исследования свойств порталов
is_gel(blue_moondust).
can_soar(blue_moondust, chell). % Синий гель позволяет высоко подпрыгивать, если на него наступить
is_gel(red_moondust).
can_speedup(red_moondust, chell). % Красный ускоряет передвижение
is_gel(gray_moondust).
can_open_portal(gray_moondust, portal_gun). % Серый позволяет открывать портал

is_bridge(flying_glass). % Летающий мост по которому Челл может пройти
is_shield(flying_glass).

% Воздущный мост, который может перемещать человека и предметы