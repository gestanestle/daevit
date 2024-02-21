import { Post } from "@/lib/types/post";
import React from "react";
import { BsBookmarkHeart } from "react-icons/bs";
import PostFooter from "./PostFooter";

export default function PostBox({
  postId,
  title,
  flair,
  content,
  author: { username, profileImageURL },
  createdAt,
}: Post) {
  return (
    <>
      <div className="h-fit w-full sm:w-3/4 lg:w-1/2 bg-base-300 rounded-lg">
        <div className="px-4 pt-4 flex space-x-2 text-sm">
          <div className="flex-initial">
            <div className="avatar">
              <div className="w-6 rounded-full">
                <img title="User profile photo" src={profileImageURL!} />
              </div>
            </div>
          </div>
          <div className="flex-auto">
            <p>@{username}</p>
          </div>
          <div className="flex-initial w-50">
            <p>{createdAt?.toDateString()}</p>
          </div>
        </div>
        <div className="px-4 py-2">
          <div className="flex">
            <div className="flex-auto">
              <p>{title}</p>
              <div className="badge badge-warning">{flair}</div>
            </div>
            <div className="flex-initial w-25">
              <button title="Bookmark this post">
                <BsBookmarkHeart size={25} />
              </button>
            </div>
          </div>
        </div>
        <div className="px-4 py-2">
          <p>{content}</p>
        </div>
        <PostFooter postId={postId as string} />
      </div>
    </>
  );
}
