import { z } from "zod";

export const UserSchema = z.object({
  authId: z.string(),
  username: z.string(),
  lastName: z.string().nullish(),
  firstName: z.string().nullish(),
  profileImageURL: z.string(),
});

export const AuthorSchema = z.object({
  authId: z.string(),
  username: z.string(),
  profileImageURL: z.string(),
});

export type User = z.infer<typeof UserSchema>;

export type Author = z.infer<typeof AuthorSchema>;
