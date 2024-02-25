import { z } from "zod";

export const ZDate = z
  .string({
    required_error: "Date is required",
    invalid_type_error: "Date must be a string",
  })
  .transform((str: string) => new Date(str));
