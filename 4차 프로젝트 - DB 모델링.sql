CREATE TABLE `member` (
  `mem_id` integer UNIQUE PRIMARY KEY,
  `email` stirng,
  `password` string,
  `nickname` stirng,
  `gender` stirng,
  `profile_img` string,
  `goal_lv` int,
  `goal_name` stirng,
  `goal_exp` int
);

CREATE TABLE `todo` (
  `todo_id` integer UNIQUE PRIMARY KEY,
  `todo_done` boolean,
  `todo_content` string,
  `todo_date` date,
  `todo_important` boolean,
  `mem_id` int
);

CREATE TABLE `account` (
  `acc_id` integer UNIQUE PRIMARY KEY,
  `acc_date` date,
  `acc_income` boolean,
  `acc_category` string,
  `acc_amount` bigint,
  `acc_desc` string,
  `acc_payment` string,
  `acc_etc` string,
  `mem_id` int
);

CREATE TABLE `health` (
  `heal_id` integer UNIQUE PRIMARY KEY,
  `exer_name` string,
  `exer_amount` int,
  `exer_done` boolean,
  `mem_id` int
);

CREATE TABLE `note` (
  `note_id` integer UNIQUE PRIMARY KEY,
  `note_name` string,
  `note_content` string,
  `note_important` boolean,
  `mem_id` int
);

CREATE TABLE `friend` (
  `mem1_id` int,
  `mem2_id` int
);

ALTER TABLE `friend` ADD FOREIGN KEY (`mem1_id`) REFERENCES `member` (`mem_id`);

ALTER TABLE `friend` ADD FOREIGN KEY (`mem2_id`) REFERENCES `member` (`mem_id`);

ALTER TABLE `member` ADD FOREIGN KEY (`mem_id`) REFERENCES `todo` (`mem_id`);

ALTER TABLE `member` ADD FOREIGN KEY (`mem_id`) REFERENCES `account` (`mem_id`);

ALTER TABLE `member` ADD FOREIGN KEY (`mem_id`) REFERENCES `health` (`mem_id`);

ALTER TABLE `member` ADD FOREIGN KEY (`mem_id`) REFERENCES `note` (`mem_id`);
