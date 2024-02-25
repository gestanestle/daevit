"use client";

import React, { Fragment } from "react";
import { Comment, CommentSchema } from "@/lib/types/comment";
import { useInfiniteQuery } from "@tanstack/react-query";
import ComBox from "./ComBox";
import { getBaseComments } from "@/lib/actions/CommentService";

export default function ComSec({ postId }: { postId: number }) {
  const fetchComments = async ({ pageParam }: any) => {
    return await getBaseComments(postId, pageParam);
  };

  const {
    data,
    error,
    fetchNextPage,
    hasNextPage,
    isFetching,
    isFetchingNextPage,
    status,
  } = useInfiniteQuery({
    queryKey: ["comments"],
    queryFn: fetchComments,
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
          {group.data.getBaseComments
            .map((content: any) => CommentSchema.parse(content))
            .map((com: Comment) => (
              <ComBox
                commentId={com.commentId}
                content={com.content}
                author={{
                  authId: com.author.authId,
                  username: com.author.username,
                  profileImageURL: com.author.profileImageURL,
                }}
                createdAt={com.createdAt}
                updatedAt={com.updatedAt}
                likes={com.likes}
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
