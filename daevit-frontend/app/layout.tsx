import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import Navbar from "@/components/Navbar";
import { ClerkProvider } from "@clerk/nextjs";
import { dark, neobrutalism } from "@clerk/themes";

const inter = Inter({ subsets: ["latin"] });
export const metadata: Metadata = {
  title: "Daevit Forum App",
  description: "A forum application.",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <ClerkProvider
      appearance={{
        baseTheme: dark,
        signIn: { baseTheme: neobrutalism },
      }}
    >
      <html lang="en">
        <body className={inter.className}>
          <div className="container-mx-auto p-4">
            <Navbar />
            {children}
          </div>
        </body>
      </html>
    </ClerkProvider>
  );
}
