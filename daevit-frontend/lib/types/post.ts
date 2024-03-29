import { z } from "zod";
import { AuthorSchema } from "./user";
import { ZDate } from "./date";

export const PostSchema = z.object({
  postId: z.string().optional(),
  title: z.string({
    required_error: "Title is required",
    invalid_type_error: "Title must be a string",
  }),
  flair: z.string({
    required_error: "Flair is required",
    invalid_type_error: "Flair must be a string",
  }),
  content: z.string({
    required_error: "Content is required",
    invalid_type_error: "Content must be a string",
  }),
  author: AuthorSchema,
  createdAt: ZDate.optional(),
  updatedAt: ZDate.optional(),
  likes: z.number().optional(),
  comments: z.number().optional(),
  shares: z.number().optional(),
});

export type Post = z.infer<typeof PostSchema>;
