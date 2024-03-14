"use client";

import InfiniteQ from "@/components/InfiniteQ";
import Write from "@/components/Write";
import { getAllPosts } from "@/lib/actions/PostService";
import { SignedIn } from "@clerk/nextjs";
import React from "react";

export default function page() {
  const fetchPosts = async ({ pageParam }: any) => {
    return await getAllPosts(pageParam);
  };

  return (
    <>
      <div className="grid justify-items-center grid-cols-1 gap-4 py-4">
        <SignedIn>
          <Write />
        </SignedIn>
        <InfiniteQ func={fetchPosts} q="getPosts" />
      </div>
    </>
  );
}
