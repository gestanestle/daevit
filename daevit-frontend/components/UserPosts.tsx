"use client";

import { getPostsBy } from "@/lib/actions/UserService";
import InfiniteQ from "./InfiniteQ";

export default function UserPosts({ username }: { username: string }) {
  const fetchPosts = async ({ pageParam }: any) => {
    return await getPostsBy(username, pageParam, 10);
  };

  return <InfiniteQ func={fetchPosts} q="getPostsBy" />;
}
