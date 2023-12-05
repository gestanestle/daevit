import { z } from "zod";

export const UserSchema = z.object({
  authId: z.string(),
  username: z.string(),
  lastName: z.string().optional(),
  firstName: z.string().optional(),
  profileImageURL: z.string(),
});

export type User = z.infer<typeof UserSchema>;
