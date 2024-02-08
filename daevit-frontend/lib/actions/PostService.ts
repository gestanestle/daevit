import {
  PostRead,
  PostWrite,
  PostReadSchema,
  PostWriteSchema,
} from "@/lib/types/post";

export async function getAllPosts(
  pageNo: number,
  pageSize: number
): Promise<PostRead[] | undefined> {
  try {
    const res = await fetch(
      process.env.SERVER_HOST +
        `/api/v1/posts?pageNo=${pageNo}&pageSize=${pageSize}`,
      {
        method: "GET",
        next: { revalidate: 3600 },
      }
    );

    const json = await res.json();

    const posts: PostRead[] = json.data.map((content: any) =>
      PostReadSchema.parse(content)
    );

    return posts;
  } catch (e) {
    console.log(e);
    throw new Error();
  }
}

export async function submitPost(
  formData: FormData
): Promise<number | undefined> {
  const post: PostWrite = PostWriteSchema.parse({
    category: formData.get("category"),
    title: formData.get("title"),
    content: formData.get("content"),
    author: formData.get("author_authId"),
  });

  console.log(post);

  try {
    const res = await fetch(`/api/v1/posts`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(post),
      // mode: "no-cors",
    });

    if (!res.ok) {
      throw new Error(`HTTP error! Status: ${res.status}`);
    }

    const data = await res.json();
    const id = data.post_id;
    return id;
  } catch (e) {
    console.log(e);
    throw new Error();
  }
}

export async function getPost(id: number): Promise<PostRead | undefined> {
  try {
    const res = await fetch(`/api/v1/posts/${id}`, {
      method: "GET",
      next: { revalidate: 3600 },
    });

    const json = await res.json();
    const post = PostReadSchema.parse(json.data);
    return post;
  } catch (e) {
    console.log(e);
    throw new Error();
  }
}
