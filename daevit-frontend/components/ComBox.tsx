"use client";

import { Comment } from "@/lib/types/comment";
import React from "react";

export default function ComBox({
  commentId,
  content,
  author: { username, profileImageURL },
  updatedAt,
  likes,
}: Comment) {
  return (
    <>
      <div className="h-fit w-full sm:w-3/4 lg:w-1/2">
        <div className="flex px-2 space-x-2 text-sm">
          <div className="flex-initial">
            <div className="avatar">
              <div className="w-6 rounded-full">
                <img title="User profile photo" src={profileImageURL!} />
              </div>
            </div>
          </div>
          <span className="w-full space-y-2 bg-base-300 p-2 rounded-md ">
            <div className="flex flex-col gap-2">
              <span className="flex flex-row space-x-2">
                <p className="font-bold text-xs">@{username}</p>
                <p className="text-xs">{updatedAt?.toDateString()}</p>
              </span>
              <p className="text-xs">{content}</p>
            </div>
          </span>
        </div>
      </div>
    </>
  );
}
