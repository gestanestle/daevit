"use client";

import PostBox from "@/components/PostBox";
import { Post, PostSchema } from "@/lib/types/post";
import { useInfiniteQuery } from "@tanstack/react-query";
import { Fragment } from "react";

export default function page({
  func,
  q,
}: {
  func: ({ pageParam }: any) => Promise<any>;
  q: string;
}) {
  const {
    data,
    error,
    fetchNextPage,
    hasNextPage,
    isFetching,
    isFetchingNextPage,
    status,
  } = useInfiniteQuery({
    queryKey: ["posts"],
    queryFn: func,
    initialPageParam: 1,
    // getNextPageParam: (lastPage, pages) => offset + 1,
    getNextPageParam: (lastPage, allPages, lastPageParam) => {
      if (lastPage.length === 0) {
        console.log("lastpage len: " + lastPage.length);
        return undefined;
      }
      return lastPageParam + 1;
    },
    getPreviousPageParam: (firstPage, allPages, firstPageParam) => {
      if (firstPageParam <= 1) {
        return undefined;
      }
      return firstPageParam - 1;
    },
  });

  return (
    <>
      {data?.pages.map((group, i) => (
        <Fragment key={i}>
          {group.data &&
            group.data[`${q}`] &&
            group.data[`${q}`]
              .map((content: any) => PostSchema.parse(content))
              .map((post: Post) => (
                <PostBox
                  postId={post.postId}
                  title={post.title}
                  flair={post.flair}
                  content={post.content}
                  createdAt={post.createdAt}
                  author={{
                    authId: post.author.authId,
                    username: post.author.username,
                    profileImageURL: post.author.profileImageURL,
                  }}
                  likes={post.likes}
                  comments={post.comments}
                  shares={post.shares}
                />
              ))}
        </Fragment>
      ))}
      <div>
        <button
          className="btn btn-info"
          onClick={() => fetchNextPage()}
          disabled={!hasNextPage || isFetchingNextPage}
        >
          {isFetchingNextPage
            ? "Loading more..."
            : hasNextPage
              ? "Load More"
              : "Nothing more to load"}
        </button>
      </div>
      <div>{isFetching && !isFetchingNextPage ? "Fetching..." : null}</div>
    </>
  );
}
