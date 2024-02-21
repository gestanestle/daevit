import PostBox from "@/components/PostBox";
import { getPost } from "@/lib/actions/PostService";
import { Post } from "@/lib/types/post";
import { SignedIn } from "@clerk/nextjs";

export default async function page({ params }: { params: { page: number } }) {
  const post: Post | undefined = await getPost(params.page);
  return (
    <>
      <div className="grid justify-items-center grid-cols-1 gap-4 py-4">
        <SignedIn>
          {post != undefined && (
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
            />
          )}
        </SignedIn>
      </div>
    </>
  );
}
