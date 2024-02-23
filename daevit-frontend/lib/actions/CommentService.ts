import { CommentSchema } from "../types/comment";

export async function getBaseComments(postId: number, offset: number) {
  const query = {
    query: `
      query {
        getBaseComments(postId: ${postId}, offset: ${offset}, count: 10) {
          commentId
          post {
              postId
          }
          parent {
              commentId
          }
          content
          author {
              authId
              username
              profileImageURL
          }
          createdAt
          updatedAt
          likes
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
    next: { revalidate: 3600 },
  });
  return res.json();
}

export async function doComment(formData: FormData) {
  const com = CommentSchema.parse({
    content: formData.get("content"),
    author: {
      authId: formData.get("author"),
    },
    post: {
      postId: formData.get("postId"),
    },
  });

  const mutation = {
    query: `
      mutation {
        createComment(
          postId: ${parseInt(com.post?.postId as string)},
          content:" ${com.content}",
          author: "${com.author.authId}"
          ) {
          commentId
          post {
              postId
          }
          parent {
              commentId
          }
          content
          author {
            authId
            username
            profileImageURL
          }
          createdAt
          updatedAt
          likes
        }
      }
    `,
  };

  console.log(com);

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
