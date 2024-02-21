import PostBox from "@/components/PostBox";
import Write from "@/components/Write";
import { getAllPosts, hasLike } from "@/lib/actions/PostService";
import { Post } from "@/lib/types/post";
import { SignedIn } from "@clerk/nextjs";

export default async function page({ params }: { params: { page: number } }) {
  const posts: Post[] | undefined = await getAllPosts(params.page, 50);
  return (
    <>
      <div className="grid justify-items-center grid-cols-1 gap-4 py-4">
        <SignedIn>
          <Write />
          {posts?.map((post) => (
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
        </SignedIn>
      </div>
    </>
  );
}
