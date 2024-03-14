import { Post, PostSchema } from "@/lib/types/post";

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

export async function getAllPosts(offset: number) {
  const query = {
    query: `
      query {
        getPosts(offset: ${offset}, count: 10) {
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
          likes
          comments
          shares
        }
      }
    `,
  };

  const res = await fetch(`/api/v1/graphql`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(query),
  });
  return res.json();
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
          createdAt
          updatedAt
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
  const postId = parseInt(formData.get("postId") as string);
  const authId = formData.get("authId");

  const mutation = {
    query: `
      mutation {
        doLike(
          postId: ${postId}, 
          authId: "${authId}"
        )
      }
    `,
  };

  try {
    await fetch(`/api/v1/graphql`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(mutation),
    });
  } catch (e) {
    console.log(e);
  }
}

export async function hasLike(postId: string, authId: string) {
  const query = {
    query: `
      query {
        hasLike(
          postId: ${postId}, 
          authId: "${authId}"
          ) 
      }
      
    `,
  };

  try {
    const res = await fetch(`/api/v1/graphql`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(query),
    });
    const json = await res.json();
    return json.data.hasLike;
  } catch (e) {
    console.log(e);
  }
}

export async function doShare(formData: FormData) {
  const postId = parseInt(formData.get("postId") as string);
  const authId = formData.get("authId");

  const mutation = {
    query: `
      mutation {
        doShare(
          postId: ${postId}, 
          authId: "${authId}"
        )
      }
      
    `,
  };

  try {
    await fetch(`/api/v1/graphql`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(mutation),
    });
  } catch (e) {
    console.log(e);
  }
}

export async function hasShare(postId: string, authId: string) {
  const query = {
    query: `
      query {
        hasShare(
          postId: ${postId}, 
          authId: "${authId}"
          ) 
      }
    `,
  };
  try {
    const res = await fetch(`/api/v1/graphql`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(query),
    });
    const json = await res.json();
    return json.data.hasShare;
  } catch (e) {
    console.log(e);
  }
}
