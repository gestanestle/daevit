CREATE TABLE IF NOT EXISTS daevit_user (
     user_id bigint not null PRIMARY KEY,
     auth_id varchar(255),
     birthdate varchar(255),
     created_at timestamp(6),
     email varchar(255),
     first_name varchar(255),
     last_name varchar(255),
     profile_imageurl varchar(255),
     updated_at timestamp(6),
     username varchar(255)
);

CREATE TABLE IF NOT EXISTS post (
      post_id bigint not null PRIMARY KEY,
      category varchar(255) check (category in ('DISCUSSION','HELP','MEMES')),
      flair varchar(255),
      content text,
      created_at timestamp(6),
      title varchar(255),
      updated_at timestamp(6),
      author_user_id bigint references daevit_user(user_id) on delete cascade
);

CREATE TABLE IF NOT EXISTS post_like (
    post_id bigint references post(post_id) on delete cascade,
    liked_by bigint references daevit_user(user_id) on delete cascade,
    liked_at timestamp(6),
    primary key (post_id, liked_by)
);

CREATE INDEX user_auth ON daevit_user (auth_id);

ALTER TABLE daevit_user
    ALTER COLUMN user_id
        ADD GENERATED ALWAYS AS IDENTITY;

ALTER TABLE post
    ALTER COLUMN post_id
        ADD GENERATED ALWAYS AS IDENTITY;