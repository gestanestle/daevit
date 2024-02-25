import { User, UserSchema } from "../types/user";

export async function getUserBy(username: string): Promise<User | undefined> {
  const query = {
    query: `
      query {
        getUserBy(username: "${username}") {
          authId
          username
          email
          birthdate
          lastName
          firstName
          profileImageURL
          createdAt 
          updatedAt
        }
      }
    `,
  };
  
  try {
    const res = await fetch(process.env.SERVER_HOST + `api/v1/graphql`, {
      method: "POST",
      next: { revalidate: 3600 },
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(query),
    });

    const json = await res.json();
    return UserSchema.parse(json.data.getUserBy);
  } catch (e) {
    console.log(e);
  }
}
