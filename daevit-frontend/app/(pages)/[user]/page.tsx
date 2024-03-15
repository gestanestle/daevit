import { MdCalendarMonth, MdCheckCircleOutline } from "react-icons/md";

import { getPostsBy, getUserBy } from "@/lib/actions/UserService";
import UserPosts from "@/components/UserPosts";
import Write from "@/components/Write";
import { SignedIn } from "@clerk/nextjs";

export default async function User({ params }: { params: { user: string } }) {
  const user = await getUserBy(params.user);
  // bg-gradient-to-r from-pink-500 to-rose-500
  return (
    <>
      <section className="flex justify-center my-4 text-black">
        <div className="card w-1/2 bg-gradient-to-tr from-amber-500 to-indigo-600">
          <div className="flex justify-center items-center h-60 w-full">
            <div className="avatar">
              <div className="w-48 rounded-full ring ring-primary ring-offset-base-100 ring-offset-2">
                <img title="pfp" src={user?.profileImageURL} />
              </div>
            </div>
          </div>
          <div className="card-body items-center text-center glass">
            <h2 className="card-title">@{user?.username}</h2>
            <p>
              {user?.firstName} {user?.lastName}
            </p>

            <span className="flex flex-row items-center gap-2">
              <MdCalendarMonth />
              <p>Joined: {user?.createdAt?.toDateString()}</p>
            </span>

            <div className="card-actions">
              <button className="btn btn-primary">
                <MdCheckCircleOutline />
                Follow
              </button>
            </div>
          </div>
        </div>
      </section>

      <section>
        <div className="grid justify-items-center grid-cols-1 gap-4 py-4">
          <div className="w-1/2 border-y-2 py-2 border-blue-400 bg-none text-center">
            <p className="font-bold text-lg">Posts</p>
          </div>
          <UserPosts username={user!.username} />
        </div>
      </section>
    </>
  );
}
