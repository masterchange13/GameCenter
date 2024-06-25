-- 向 latest_events 表插入数据
INSERT INTO latest_events (event_id, event_name, event_time, event_description)
VALUES
    (1, 'League of Legends Championship', '2024-07-15 18:00:00', 'The annual championship event for League of Legends.'),
    (2, 'Counter-Strike: Global Offensive Major', '2024-08-10 12:00:00', 'The biggest CS:GO event featuring top teams from around the world.');

-- 向 games 表插入数据
INSERT INTO games (game_id, game_name, game_type, release_date, developer, price, description, platform)
VALUES
    (1, 'League of Legends', 'MOBA', '2009-10-27', 'Riot Games', 0.00, 'League of Legends is a fast-paced, competitive online game that blends the speed and intensity of an RTS with RPG elements.', 'PC'),
    (2, 'Counter-Strike: Global Offensive', 'FPS', '2012-08-21', 'Valve Corporation', 19.99, 'Counter-Strike: Global Offensive (CS:GO) expands upon the team-based action gameplay that it pioneered when it was launched 19 years ago.', 'PC, Xbox, PlayStation');

-- 向 recommended_games 表插入数据
INSERT INTO recommended_games (recommendation_id, game_name, game_description, game_id)
VALUES
    (1, 'League of Legends', 'League of Legends is a fast-paced, competitive online game that blends the speed and intensity of an RTS with RPG elements.', 1),
    (2, 'Counter-Strike: Global Offensive', 'Counter-Strike: Global Offensive (CS:GO) expands upon the team-based action gameplay that it pioneered when it was launched 19 years ago.', 2);

-- 向 game_stars 表插入数据
INSERT INTO game_stars (star_id, star_name, star_description, star_photo)
VALUES
    (1, 'Faker', 'Lee Sang-hyeok, better known by his in-game name Faker, is a South Korean professional League of Legends player.', 'faker.jpg'),
    (2, 's1mple', 'Oleksandr Kostyliev, better known as s1mple, is a Ukrainian professional Counter-Strike: Global Offensive player.', 's1mple.jpg');
INSERT INTO game_stars (star_id, star_name, star_description, star_photo)
VALUES
    (3, 'N0tail', 'Johan Sundstein, better known as N0tail, is a Danish professional Dota 2 player and captain of OG.', 'n0tail.jpg'),
    (4, 'Dendi', 'Danil Ishutin, better known as Dendi, is a Ukrainian professional Dota 2 player.', 'dendi.jpg'),
    (5, 'shroud', 'Michael Grzesiek, better known as shroud, is a Canadian streamer and former professional Counter-Strike: Global Offensive player.', 'shroud.jpg'),
    (6, 'Doublelift', 'Yiliang Peng, better known as Doublelift, is a retired American professional League of Legends player.', 'doublelift.jpg'),
    (7, 'ZywOo', 'Mathieu Herbaut, better known as ZywOo, is a French professional Counter-Strike: Global Offensive player.', 'zywoo.jpg'),
    (8, 'Caps', 'Rasmus Winther, better known as Caps, is a Danish professional League of Legends player.', 'caps.jpg'),
    (9, 'Miracle-', 'Amer Al-Barkawi, better known as Miracle-, is a Jordanian-Polish professional Dota 2 player.', 'miracle.jpg'),
    (10, 'Uzi', 'Jian Zihao, better known as Uzi, is a retired Chinese professional League of Legends player.', 'uzi.jpg'),
    (11, 'coldzera', 'Marcelo David, better known as coldzera, is a Brazilian professional Counter-Strike: Global Offensive player.', 'coldzera.jpg'),
    (12, 'kennyS', 'Kenny Schrub, better known as kennyS, is a French professional Counter-Strike: Global Offensive player.', 'kennys.jpg'),
    (13, 'Ninja', 'Richard Tyler Blevins, better known as Ninja, is an American streamer and professional gamer.', 'ninja.jpg'),
    (14, 'Chovy', 'Jeong Ji-hoon, better known as Chovy, is a South Korean professional League of Legends player.', 'chovy.jpg'),
    (15, 'SumaiL', 'Syed Sumail Hassan, better known as SumaiL, is a Pakistani-American professional Dota 2 player.', 'sumail.jpg'),
    (16, 'TACO', 'Epitácio de Melo Filho, better known as TACO, is a Brazilian professional Counter-Strike: Global Offensive player.', 'taco.jpg'),
    (17, 'ScreaM', 'Adil Benrlitom, better known as ScreaM, is a Belgian professional Valorant player and former Counter-Strike: Global Offensive player.', 'scream.jpg'),
    (18, 'Perkz', 'Luka Perković, better known as Perkz, is a Croatian professional League of Legends player.', 'perkz.jpg'),
    (19, 'Ana', 'Anathan Pham, better known as Ana, is an Australian professional Dota 2 player.', 'ana.jpg'),
    (20, 'Rekkles', 'Carl Martin Erik Larsson, better known as Rekkles, is a Swedish professional League of Legends player.', 'rekkles.jpg');


-- 向 game_guides 表插入数据
INSERT INTO game_guides (guide_id, game_name, guide_content)
VALUES
    (1, 'League of Legends', '1. Farming minions efficiently is key to gaining gold and experience early in the game. 2. Warding key locations helps prevent ganks and provides vision for your team.'),
    (2, 'Counter-Strike: Global Offensive', '1. Communication and teamwork are essential. 2. Learn recoil patterns for better accuracy.');
INSERT INTO game_guides (guide_id, game_name, guide_content)
VALUES
    (3, 'Dota 2', '1. Understanding hero roles is crucial for team composition. 2. Use the courier effectively to maximize farm.'),
    (4, 'Overwatch', '1. Team composition and switching heroes according to the situation is essential. 2. Coordinate ultimates for maximum impact.'),
    (5, 'Valorant', '1. Map control is key to winning rounds. 2. Use abilities strategically to gain an advantage.'),
    (6, 'Apex Legends', '1. Stay aware of the shrinking circle and position accordingly. 2. Communication with your squad can make a big difference.'),
    (7, 'Fortnite', '1. Building quickly and efficiently can give you a strategic advantage. 2. Stay aware of the storm and plan your movements accordingly.'),
    (8, 'PUBG', '1. Choosing the right landing spot can set the tone for the rest of the game. 2. Use cover and terrain to your advantage.'),
    (9, 'Call of Duty: Warzone', '1. Manage your loadout carefully and pick weapons that suit your playstyle. 2. Use contracts to gain money and other advantages.'),
    (10, 'Rainbow Six Siege', '1. Learning the maps and knowing where to place gadgets is crucial. 2. Communication with your team can make or break a round.'),
    (11, 'StarCraft II', '1. Mastering macro and micro management is key to success. 2. Learn and adapt to your opponent\'s strategy.'),
    (12, 'Hearthstone', '1. Building a balanced deck is crucial for success. 2. Knowing when to play specific cards can turn the tide of a match.'),
    (13, 'Rocket League', '1. Ball control and positioning are essential. 2. Coordinate with your teammates for effective plays.'),
    (14, 'FIFA', '1. Mastering different types of passes and shots can give you an edge. 2. Use tactics and formations that suit your playstyle.'),
    (15, 'Street Fighter V', '1. Learning your character\'s combos and matchups is crucial. 2. Stay aware of your opponent\'s tendencies and adapt accordingly.'),
    (16, 'Mortal Kombat 11', '1. Master your character\'s moveset and combos. 2. Practice blocking and countering effectively.'),
    (17, 'World of Warcraft', '1. Understanding your class and role in a raid or dungeon is essential. 2. Stay updated with the latest patches and changes to the game.'),
    (18, 'Minecraft', '1. Gathering resources efficiently is key to building and surviving. 2. Exploring different biomes can provide unique resources and opportunities.'),
    (19, 'The Legend of Zelda: Breath of the Wild', '1. Experiment with different combinations of weapons and abilities. 2. Explore the world thoroughly to find hidden treasures and secrets.'),
    (20, 'Animal Crossing: New Horizons', '1. Customize your island to suit your preferences and attract new villagers. 2. Participate in seasonal events and activities for unique rewards.');


-- 向 recommended_games 表插入数据

-- 向 recommended_games_events 表插入数据
INSERT INTO recommended_games_events (recommendation_id, event_id)
VALUES
    (1, 1), -- League of Legends recommended for League of Legends Championship
    (2, 2); -- Counter-Strike: Global Offensive recommended for CS:GO Major

-- 向 recommended_games_stars 表插入数据
INSERT INTO recommended_games_stars (recommendation_id, star_id)
VALUES
    (1, 1), -- Faker recommends League of Legends
    (2, 2); -- s1mple recommends Counter-Strike: Global Offensive

-- 插入数据到 guide_comments 表
INSERT INTO guide_comments (guide_id, commenter_name, comment_content) VALUES
                                                                           (1, 'Alice', 'Great guide for League of Legends! Really helped me improve my farming.'),
                                                                           (1, 'Bob', 'Very useful tips on warding. Thanks!'),
                                                                           (2, 'Charlie', 'Good advice on communication and teamwork in CS:GO.'),
                                                                           (2, 'Dave', 'Learning recoil patterns made a big difference for me.'),
                                                                           (3, 'Eve', 'Understanding hero roles is crucial in Dota 2. This guide is spot on.'),
                                                                           (3, 'Frank', 'Using the courier effectively is something I always struggled with. Thanks!'),
                                                                           (4, 'Grace', 'Great Overwatch tips! Switching heroes really does make a difference.'),
                                                                           (4, 'Hank', 'Coordinating ultimates is something my team needs to work on.'),
                                                                           (5, 'Ivy', 'Valorant map control tips are very helpful.'),
                                                                           (5, 'Jack', 'Using abilities strategically gave me an edge in my last match.'),
                                                                           (6, 'Kelly', 'Staying aware of the circle is key in Apex Legends. Great guide!'),
                                                                           (6, 'Leo', 'Communication with my squad improved our gameplay significantly.'),
                                                                           (7, 'Mona', 'Building quickly in Fortnite has always been a challenge for me. Thanks for the tips!'),
                                                                           (7, 'Ned', 'Staying aware of the storm is something I often forget.'),
                                                                           (8, 'Olivia', 'Choosing the right landing spot in PUBG really sets the tone for the game.'),
                                                                           (8, 'Paul', 'Using cover and terrain to my advantage has improved my gameplay.'),
                                                                           (9, 'Quinn', 'Managing my loadout carefully in Warzone has made a big difference.'),
                                                                           (9, 'Rita', 'Using contracts to gain money and advantages is a great tip.'),
                                                                           (10, 'Sam', 'Learning the maps in Rainbow Six Siege is crucial. Great guide!'),
                                                                           (10, 'Tina', 'Communication with my team has improved our rounds significantly.'),
                                                                           (11, 'Uma', 'Mastering macro and micro management in StarCraft II is key. Thanks for the tips.'),
                                                                           (11, 'Victor', 'Learning and adapting to my opponent\'s strategy has improved my gameplay.'),
                                                                           (12, 'Wendy', 'Building a balanced deck in Hearthstone is crucial. Great guide!'),
                                                                           (12, 'Xander', 'Knowing when to play specific cards has turned the tide in many matches.'),
                                                                           (13, 'Yara', 'Ball control and positioning in Rocket League are essential.'),
                                                                           (13, 'Zane', 'Coordinating with my teammates has made our plays more effective.'),
                                                                           (14, 'Alice', 'Mastering different types of passes and shots in FIFA has given me an edge.'),
                                                                           (14, 'Bob', 'Using tactics and formations that suit my playstyle has improved my game.'),
                                                                           (15, 'Charlie', 'Learning combos and matchups in Street Fighter V is crucial. Great guide!'),
                                                                           (15, 'Dave', 'Staying aware of my opponent\'s tendencies has helped me adapt.'),
                                                                           (16, 'Eve', 'Mastering my character\'s moveset and combos in Mortal Kombat 11 is key.'),
                                                                           (16, 'Frank', 'Practicing blocking and countering has improved my defense.'),
                                                                           (17, 'Grace', 'Understanding my class and role in World of Warcraft is essential. Thanks for the tips.'),
                                                                           (17, 'Hank', 'Staying updated with the latest patches and changes to the game has made a big difference.'),
                                                                           (18, 'Ivy', 'Gathering resources efficiently in Minecraft is key to building and surviving.'),
                                                                           (18, 'Jack', 'Exploring different biomes has provided unique resources and opportunities.'),
                                                                           (19, 'Kelly', 'Experimenting with different combinations of weapons and abilities in Breath of the Wild is fun.'),
                                                                           (19, 'Leo', 'Exploring the world thoroughly has helped me find hidden treasures and secrets.'),
                                                                           (20, 'Mona', 'Customizing my island in Animal Crossing: New Horizons has been a lot of fun.'),
                                                                           (20, 'Ned', 'Participating in seasonal events and activities has given me unique rewards.');
