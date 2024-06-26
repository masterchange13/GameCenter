CREATE DATABASE IF NOT EXISTS game;
USE game;

-- 创建最新赛事表
CREATE TABLE latest_events (
                               event_id INT PRIMARY KEY,
                               event_name VARCHAR(100),
                               event_time DATETIME,
                               event_description TEXT
);

-- 创建游戏表
CREATE TABLE games (
                       game_id INT PRIMARY KEY,
                       game_name VARCHAR(100),
                       game_type VARCHAR(50),
                       release_date DATE,
                       developer VARCHAR(100),
                       price DECIMAL(10, 2),
                       description TEXT,
                       platform VARCHAR(50)
);

-- 创建游戏推荐表
CREATE TABLE recommended_games (
                                   recommendation_id INT PRIMARY KEY,
                                   game_name VARCHAR(100),
                                   game_description TEXT,
                                   game_id INT,
                                   FOREIGN KEY (game_id) REFERENCES games(game_id)
);

-- 创建游戏明星表
CREATE TABLE game_stars (
                            star_id INT PRIMARY KEY,
                            star_name VARCHAR(100),
                            star_description TEXT,
                            star_photo VARCHAR(255)
);

-- 创建游戏攻略表
CREATE TABLE game_guides (
                             guide_id INT PRIMARY KEY,
                             game_name VARCHAR(100),
                             guide_content TEXT
);

-- 创建中间表来建立推荐游戏和最新赛事之间的多对多关系
CREATE TABLE recommended_games_events (
                                          recommendation_id INT,
                                          event_id INT,
                                          PRIMARY KEY (recommendation_id, event_id),
                                          FOREIGN KEY (recommendation_id) REFERENCES recommended_games(recommendation_id),
                                          FOREIGN KEY (event_id) REFERENCES latest_events(event_id)
);

-- 创建中间表来建立推荐游戏和游戏明星之间的多对多关系
CREATE TABLE recommended_games_stars (
                                         recommendation_id INT,
                                         star_id INT,
                                         PRIMARY KEY (recommendation_id, star_id),
                                         FOREIGN KEY (recommendation_id) REFERENCES recommended_games(recommendation_id),
                                         FOREIGN KEY (star_id) REFERENCES game_stars(star_id)
);

-- 创建评论表
CREATE TABLE guide_comments (
                                comment_id INT PRIMARY KEY AUTO_INCREMENT,
                                guide_id INT,
                                commenter_name VARCHAR(100),
                                comment_content TEXT,
                                FOREIGN KEY (guide_id) REFERENCES game_guides(guide_id)
);


-- 创建索引优化查询
CREATE INDEX idx_game_guides_game_name ON game_guides(game_name);


SHOW CREATE TABLE recommended_games_stars;
-- Step 1: Drop foreign key constraint
ALTER TABLE recommended_games_stars DROP FOREIGN KEY recommended_games_stars_ibfk_2;

-- Step 2: Modify column to auto increment
ALTER TABLE game_stars MODIFY star_id INT NOT NULL AUTO_INCREMENT;

-- Step 3: Add foreign key constraint back
ALTER TABLE recommended_games_stars
    ADD CONSTRAINT recommended_games_stars_ibfk_2
        FOREIGN KEY (star_id) REFERENCES game_stars(star_id);
