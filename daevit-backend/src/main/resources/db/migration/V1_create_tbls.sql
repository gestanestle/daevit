CREATE TABLE IF NOT EXISTS daevit_user (
     user_id bigint not null,
     auth_id varchar(255),
     birthdate varchar(255),
     created_at timestamp(6),
     email varchar(255),
     first_name varchar(255),
     last_name varchar(255),
     profile_imageurl varchar(255),
     updated_at timestamp(6),
     username varchar(255),
     primary key (user_id)
)

CREATE TABLE IF NOT EXISTS post (
      post_id bigint not null,
      category varchar(255) check (category in ('DISCUSSION','HELP','MEMES')),
      content text,
      created_at timestamp(6),
      title varchar(255),
      updated_at timestamp(6),
      author_user_id bigint,
      primary key (post_id),
      constraint user_post
        foreign key (author_user_id)
            references daevit_user(user_id)
)