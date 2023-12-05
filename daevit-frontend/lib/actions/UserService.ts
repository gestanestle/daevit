import { User, UserSchema } from "../types/user";

export async function getUser(authId: string): Promise<User | undefined> {
  try {
    const res = await fetch(
      process.env.SERVER_HOST + `/api/v1/user/${authId}`,
      { method: "GET" }
    );
    const data = await res.json();

    const user: User = UserSchema.parse(data);
    return user;
  } catch (e) {
    console.log(e);
    throw new Error();
  }
}
