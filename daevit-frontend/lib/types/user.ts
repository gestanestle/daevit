import { z } from "zod";
import { ZDate } from "./date";

export const UserSchema = z.object({
  authId: z.string(),
  username: z.string(),
  email: z.string(),
  birthdate: ZDate.nullish(),
  lastName: z.string().nullish(),
  firstName: z.string().nullish(),
  profileImageURL: z.string(),
  createdAt: ZDate,
  updatedAt: ZDate
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
