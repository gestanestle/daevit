import Post from "@/components/Post";
import Write from "@/components/Write";
import { getAllPosts } from "@/lib/actions/PostService";
import { PostRead, PostReadSchema } from "@/lib/types/post";
import { SignedIn } from "@clerk/nextjs";

export default async function page({ params }: { params: { page: number } }) {
  const posts: PostRead[] | undefined = await getAllPosts(params.page, 50);

  console.log(process.env.SERVER_HOST);
  return (
    <>
      <div className="grid justify-items-center grid-cols-1 gap-4 py-4">
        <SignedIn>
          <Write />
          {posts?.map((post) => (
            <Post
              title={post.title}
              content={post.content}
              createdAt={post.createdAt}
              author={{
                authId: post.author.authId,
                username: post.author.username,
                profileImageURL: post.author.profileImageURL,
              }}
            />
          ))}
        </SignedIn>
      </div>
    </>
  );
}
