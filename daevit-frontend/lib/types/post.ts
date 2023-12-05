import { z } from "zod";
import { UserSchema } from "./user";
import { type } from "os";

const ZDate = z.string().transform((str: string) => new Date(str));

export const PostWriteSchema = z.object({
  postId: z.number().optional(),
  title: z.string({
    required_error: "Title is required",
    invalid_type_error: "Title must be a string",
  }),
  content: z.string({
    required_error: "Content is required",
    invalid_type_error: "Content must be a string",
  }),
  author: z.string(),
  createdAt: ZDate.optional(),
  updatedAt: ZDate.optional(),
});

export const PostReadSchema = z.object({
  postId: z.number().optional(),
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

export type PostWrite = z.infer<typeof PostWriteSchema>;
export type PostRead = z.infer<typeof PostReadSchema>;
