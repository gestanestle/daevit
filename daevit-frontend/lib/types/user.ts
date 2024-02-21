import { z } from "zod";

export const UserSchema = z.object({
  authId: z.string(),
  username: z.string(),
  lastName: z.string().nullish(),
  firstName: z.string().nullish(),
  profileImageURL: z.string(),
});

export const AuthorSchema = z.object({
  authId: z.string({
    required_error: "AuthID is required",
    invalid_type_error: "AuthID must be a string",
  }),
  username: z.string().nullish(),
  profileImageURL: z.string().nullish(),
});

export type User = z.infer<typeof UserSchema>;

export type Author = z.infer<typeof AuthorSchema>;
