import { PostRead } from "@/lib/types/post";
import { User } from "@/lib/types/user";
import { Author } from "next/dist/lib/metadata/types/metadata-types";
import React from "react";
import {
  BsBookmarkHeart,
  BsChatRightDots,
  BsHeart,
  BsShare,
} from "react-icons/bs";

export default function Post({
  title,
  content,
  author: { username, profileImageURL },
  createdAt,
}: PostRead) {
  return (
    <>
      <div className="h-fit w-1/2 bg-base-300 rounded-lg">
        <div className="px-4 pt-4 flex space-x-2 text-sm">
          <div className="flex-initial">
            <div className="avatar">
              <div className="w-6 rounded-full">
                <img title="User profile photo" src={profileImageURL} />
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
              <div className="badge badge-warning">discussion</div>
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
        <div className="px-4 pt-2 pb-4 grid grid-cols-3 justify-items-center border border-t-2 border-purple-950 text-sm">
          <div className="flex flex-row space-x-2">
            <button title="Like this post">
              <BsHeart />
            </button>
            <p>18.k likes</p>
          </div>
          <div className="flex flex-row space-x-2">
            <button title="Comment on this post">
              <BsChatRightDots />
            </button>
            <p>102 comments</p>
          </div>
          <div className="flex flex-row space-x-2">
            <button title="Share this post">
              <BsShare />
            </button>
            <p>66 shares</p>
          </div>
        </div>
      </div>
    </>
  );
}
