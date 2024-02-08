import { User, UserSchema } from "../types/user";

export async function getUser(authId: string): Promise<User | undefined> {
  try {
    const res = await fetch(`/api/v1/user/${authId}`, { method: "GET" });
    const json = await res.json();

    const user: User = UserSchema.parse(json.data);
    return user;
  } catch (e) {
    console.log(e);
    throw new Error();
  }
}
