"use client";

import { doLike, hasLike } from "@/lib/actions/PostService";
import { useUser } from "@clerk/nextjs";
import React, { useEffect, useState } from "react";
import { BsHeartFill, BsHeart, BsChatRightDots, BsShare } from "react-icons/bs";

const handleLike = async (formData: FormData) => {
  const postId = await doLike(formData);
};

export default function LikeBox({ postId }: { postId: string }) {
  const clerkUser = useUser();
  const user = clerkUser.user;
  const authId = user?.id as string;

  const [isLiked, setIsLiked] = useState(false);

  async function fetchLike(postId: string) {
    const res = await hasLike(postId, authId);
    setIsLiked(res);
  }
  fetchLike(postId);

  return (
    <div className="px-4 pt-2 pb-4 grid grid-cols-3 justify-items-center border border-t-2 border-purple-950 text-sm">
      <div className="flex flex-row space-x-2">
        <form
          action={handleLike}
          onSubmit={() => {
            isLiked ? setIsLiked(false) : setIsLiked(true);
          }}
        >
          <input name="postId" value={postId} hidden />
          <input name="authId" value={authId} hidden />
          <button type="submit" title="Like this post">
            {isLiked ? <BsHeartFill /> : <BsHeart />}
          </button>
        </form>
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
  );
}
