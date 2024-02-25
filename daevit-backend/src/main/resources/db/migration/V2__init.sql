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
      title varchar(255),
      flair varchar(255),
      content text,
      created_at timestamp(6),
      updated_at timestamp(6),
      author_user_id bigint references daevit_user(user_id) on delete cascade
);

CREATE TABLE IF NOT EXISTS post_like (
    post_id bigint references post(post_id) on delete cascade,
    liked_by bigint references daevit_user(user_id) on delete cascade,
    liked_at timestamp(6),
    primary key (post_id, liked_by)
);

CREATE TABLE IF NOT EXISTS post_share (
    post_id bigint references post(post_id) on delete cascade,
    shared_by bigint references daevit_user(user_id) on delete cascade,
    shared_at timestamp(6),
    primary key (post_id, shared_by)
);

CREATE TABLE IF NOT EXISTS comment (
    comment_id bigint not null PRIMARY KEY,
    post_id bigint references post(post_id) on delete cascade,
    parent bigint references comment(comment_id),
    author_user_id bigint references daevit_user(user_id) on delete cascade,
    content text,
    created_at timestamp(6),
    updated_at timestamp(6)
);

CREATE INDEX user_auth ON daevit_user (auth_id);

ALTER TABLE daevit_user
    ALTER COLUMN user_id
        ADD GENERATED ALWAYS AS IDENTITY;

ALTER TABLE post
    ALTER COLUMN post_id
        ADD GENERATED ALWAYS AS IDENTITY;

ALTER TABLE comment
    ALTER COLUMN comment_id
        ADD GENERATED ALWAYS AS IDENTITY;