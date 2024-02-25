"use client";

import { doComment } from "@/lib/actions/CommentService";
import { doLike, doShare, hasLike, hasShare } from "@/lib/actions/PostService";
import { useUser } from "@clerk/nextjs";
import React, { useState } from "react";
import {
  BsHeartFill,
  BsHeart,
  BsChatRightDots,
  BsShare,
  BsShareFill,
  BsSend,
  BsChatRightDotsFill,
} from "react-icons/bs";

type LCS = {
  postId: string;
  likes: number;
  comments: number;
  shares: number;
};

const handleLike = async (formData: FormData) => {
  await doLike(formData);
};

const handleComment = async (formData: FormData) => {
  await doComment(formData);
  window.location.reload();
};

const handleShare = async (formData: FormData) => {
  await doShare(formData);
};

export default function PostFooter({ postId, likes, comments, shares }: LCS) {
  const clerkUser = useUser();
  const user = clerkUser.user;
  const authId = user?.id as string;
  const pfp = user?.imageUrl as string;

  const [isLiked, setIsLiked] = useState(false);
  const [isShared, setIsShared] = useState(false);
  const [isComsecVisible, setIsComsecVisible] = useState(false);

  const [lcs, setLcs] = useState<LCS>({
    postId: postId,
    likes: likes,
    comments: comments,
    shares: shares,
  });

  async function fetchLike(postId: string) {
    const res = await hasLike(postId, authId);
    setIsLiked(res);
  }

  async function fetchShare(postId: string) {
    const res = await hasShare(postId, authId);
    setIsShared(res);
  }

  fetchLike(postId);
  fetchShare(postId);

  return (
    <>
      <div className="px-4 pt-2 pb-2 grid grid-cols-3 justify-items-center border-y-2 border-base-100 text-sm">
        <div className="flex flex-row space-x-2">
          <form
            action={handleLike}
            onSubmit={() => {
              if (isLiked) {
                setIsLiked(false);
                setLcs((lcs) => ({
                  ...lcs,
                  likes: lcs.likes - 1,
                  shares: lcs.shares,
                }));
              } else {
                setIsLiked(true);
                setLcs((lcs) => ({
                  ...lcs,
                  likes: lcs.likes + 1,
                  shares: lcs.shares,
                }));
              }
            }}
          >
            <input name="postId" value={postId} hidden />
            <input name="authId" value={authId} hidden />
            <button type="submit" title="Like this post">
              {isLiked ? <BsHeartFill /> : <BsHeart />}
            </button>
          </form>
          <p>{lcs.likes} likes</p>
        </div>
        <div className="flex flex-row space-x-2">
          <button
            title="Comment on this post"
            onClick={() => {
              isComsecVisible
                ? setIsComsecVisible(false)
                : setIsComsecVisible(true);
            }}
          >
            {isComsecVisible ? <BsChatRightDotsFill /> : <BsChatRightDots />}
          </button>
          <p>{lcs.comments} comments</p>
        </div>
        <div className="flex flex-row space-x-2">
          <form
            action={handleShare}
            onSubmit={() => {
              if (isShared) {
                setIsShared(false);
                setLcs((lcs) => ({
                  ...lcs,
                  likes: lcs.likes,
                  shares: lcs.shares - 1,
                }));
              } else {
                setIsShared(true);
                setLcs((lcs) => ({
                  ...lcs,
                  likes: lcs.likes,
                  shares: lcs.shares + 1,
                }));
              }
            }}
          >
            <input name="postId" value={postId} hidden />
            <input name="authId" value={authId} hidden />
            <button type="submit" title="Share this post">
              {isShared ? <BsShareFill /> : <BsShare />}
            </button>
          </form>
          <p>{lcs.shares} shares</p>
        </div>
      </div>
      {isComsecVisible && (
        <>
          <div className="px-4 py-4 flex space-x-4 text-sm">
            <div className="flex-initial">
              <div className="avatar">
                <div className="w-6 rounded-full">
                  <img title="User profile photo" src={pfp} />
                </div>
              </div>
            </div>
            <span className="w-full ">
              <form action={handleComment}>
                <label className="input input-bordered flex items-center gap-2 text-sm">
                  <input
                    name="content"
                    type="text"
                    className="grow bg-base-100"
                    placeholder="Post a comment..."
                  />
                  <input name="postId" value={postId} hidden />
                  <input name="author" value={authId} hidden />
                  <button title="send-comment" type="submit">
                    <BsSend />
                  </button>
                </label>
              </form>
            </span>
          </div>
        </>
      )}
    </>
  );
}
