import { z } from "zod";
import { AuthorSchema } from "./user";
import { ZDate } from "./date";

export const CommentSchema = z.object({
  commentId: z.string().optional(),
  post: z
    .object({
      postId: z.string(),
    })
    .optional(),
  parent: z
    .object({
      commentId: z.string().optional(),
    })
    .nullish(),
  content: z.string({ required_error: "Content is required" }),
  author: AuthorSchema,
  createdAt: ZDate.optional(),
  updatedAt: ZDate.optional(),
  likes: z.number().optional(),
});

export type Comment = z.infer<typeof CommentSchema>;
