// "use client";

// import { User } from "../types/user";
// import { useUser } from "@clerk/nextjs";
// import { createContext, useEffect, useState } from "react";
// import { getUser } from "../actions/UserService";

// const user_placeholder: User = {
//   authId: "",
//   username: "",
//   lastName: "",
//   firstName: "",
//   profileImageURL: "",
// };

// export type UserGlobalContextType = {
//   user: User;
//   setUser: React.Dispatch<React.SetStateAction<User>>;
// };

// export const UserGlobalContext = createContext<UserGlobalContextType | null>(
//   null
// );

// export default function UserProvider({
//   children,
// }: {
//   children: React.ReactNode;
// }) {
//   const authUser = useUser();
//   const authId = authUser.user;
//   const [user, setUser] = useState(user_placeholder);

//   useEffect(() => {
//     async () => {
//       const user: User | undefined = await getUser(authId!);
//       setUser(user!);
//     };
//   }, [JSON.stringify(authId)]);

//   console.log("Global user: " + authId);

//   return (
//     <UserGlobalContext.Provider value={{ user, setUser }}>
//       {children}
//     </UserGlobalContext.Provider>
//   );
// }

import React from 'react'

export default function UserContext() {
  return (
    <div>UserContext</div>
  )
}
