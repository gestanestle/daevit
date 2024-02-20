import { gql } from "graphql-request";

export const GetPosts = gql`
  query getPosts($offset: offset,10) {
    getPosts(offset: $offset, 10) {
      postId
      title
      content
      author {
        authId
        username
        profileImageURL
      }
      updatedAt
    }
  }
`;
