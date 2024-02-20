import { z } from "zod";
import { AuthorSchema, UserSchema } from "./user";

const ZDate = z.string().transform((str: string) => new Date(str));

export const PostWriteSchema = z.object({
  postId: z.string().optional(),
  title: z.string({
    required_error: "Title is required",
    invalid_type_error: "Title must be a string",
  }),
  content: z.string({
    required_error: "Content is required",
    invalid_type_error: "Content must be a string",
  }),
  author: UserSchema,
  createdAt: ZDate.optional(),
  updatedAt: ZDate.optional(),
});

export const PostReadSchema = z.object({
  postId: z.string().optional(),
  title: z.string({
    required_error: "Title is required",
    invalid_type_error: "Title must be a string",
  }),
  content: z.string({
    required_error: "Content is required",
    invalid_type_error: "Content must be a string",
  }),
  author: AuthorSchema,
  createdAt: ZDate.optional(),
  updatedAt: ZDate.optional(),
});

export type PostWrite = z.infer<typeof PostWriteSchema>;
export type PostRead = z.infer<typeof PostReadSchema>;
