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

has_killed(chell, glados). % В предыдущей части Челл убивает GLADOS
has_ressurected(wheatley, glados). % Уитли случайно воскрешает GLADOS во второй части

is_weapon(portal_gun).
is_weapon(turel). % роботы которые стреляют лазерами
has_laser(turel).

is_key(usual_box). % для прохождения испытания в игре необходимо открыть дверь с помощью ключа.
% Ключем является коробка, которую необходимо поставить на определенное место

is_key(laser_box).
is_weapon(laser_box). % можно перенаправлять лазеры с помощью прозрачной стеклянной коробки
is_key(turel_box).