"use client";

import { GraphQLClient } from "graphql-request";

export const client = new GraphQLClient(`${process.env.SERVER_HOST}/graphql`);
