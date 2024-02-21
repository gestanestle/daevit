import { Post, PostSchema } from "@/lib/types/post";

export async function getAllPosts(
  pageNo: number,
  pageSize: number
): Promise<Post[] | undefined> {
  const query = {
    query: `
      query {
        getPosts(offset: ${pageNo}, count: 10) {
          postId
          title
          flair
          content
          author {
            authId
            username
            profileImageURL
          }
          likes
          comments
          shares
        }
      }
    `,
  };

  try {
    const res = await fetch(process.env.SERVER_HOST + `/api/v1/graphql`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(query),
      next: { revalidate: 3600 },
    });

    const json = await res.json();

    const posts: Post[] = json.data.getPosts.map((content: any) =>
      PostSchema.parse(content)
    );

    return posts;
  } catch (e) {
    console.log(e);
  }
}

export async function submitPost(
  formData: FormData
): Promise<number | undefined> {
  const post: Post = PostSchema.parse({
    title: formData.get("title"),
    flair: formData.get("flair"),
    content: formData.get("content"),
    author: {
      authId: formData.get("author_authId"),
    },
  });

  const mutation = {
    query: `
      mutation {
        createPost(
          title: "${post.title}" 
          flair: "${post.flair}",
          content: "${post.content}",
          author: "${post.author.authId}"
          ) {
          postId
          title
          flair
          content
          author {
            authId
            username
            profileImageURL
          }
          createdAt
          updatedAt
        }
      }
    `,
  };

  try {
    const res = await fetch(`/api/v1/graphql`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(mutation),
    });

    const json = await res.json();
    const id = json.data.createPost.postId;
    return id;
  } catch (e) {
    console.log(e);
  }
}

export async function getPost(id: number): Promise<Post | undefined> {
  const query = {
    query: `
      query {
        getPostById(postId: ${id}) {
          postId
          title
          flair
          content
          author {
            authId
            username
            profileImageURL
          }
          likes
          comments
          shares
        }
      }
    `,
  };
  try {
    const res = await fetch(process.env.SERVER_HOST + `/api/v1/graphql`, {
      method: "POST",
      next: { revalidate: 3600 },
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(query),
    });

    const json = await res.json();
    const post = PostSchema.parse(json.data.getPostById);

    return post;
  } catch (e) {
    console.log(e);
  }
}

export async function doLike(formData: FormData) {
  const body = {
    postId: parseInt(formData.get("postId") as string),
    authId: formData.get("authId"),
  };

  try {
    await fetch(`/api/v1/likes`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    });
  } catch (e) {
    console.log(e);
  }
}

export async function hasLike(postId: string, authId: string) {
  try {
    const res = await fetch(
      `/api/v1/likes?postId=${parseInt(postId)}
        &authId=${authId}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    const json = await res.json();
    return json.data;
  } catch (e) {
    console.log(e);
  }
}

export async function doShare(formData: FormData) {
  const body = {
    postId: parseInt(formData.get("postId") as string),
    authId: formData.get("authId"),
  };

  try {
    await fetch(`/api/v1/shares`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    });
  } catch (e) {
    console.log(e);
  }
}

export async function hasShare(postId: string, authId: string) {
  try {
    const res = await fetch(
      `/api/v1/shares?postId=${parseInt(postId)}
        &authId=${authId}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    const json = await res.json();
    return json.data;
  } catch (e) {
    console.log(e);
  }
}
