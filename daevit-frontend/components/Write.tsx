"use client";
import { submitPost } from "@/lib/actions/PostService";
import { useUser } from "@clerk/nextjs";
import { redirect } from "next/navigation";

const handleForm = async (formData: FormData) => {
  const postId = await submitPost(formData);
  redirect(`/posts/${postId}`);
};

export default function Write() {
  const clerkUser = useUser();
  const user = clerkUser.user;
  const authId = user?.id as string;

  const flairs: string[] = [
    "Discussion",
    "Rant",
    "Memes",
    "Question",
    "Casual",
  ];
  return (
    <>
      <div
        className="h-fit w-full sm:w-3/4 lg:w-1/2 bg-base-300 rounded-lg"
        onClick={() => document.getElementById("write_post_modal")!.showModal()}
      >
        <div className="px-4 py-4 flex space-x-2">
          <div className="flex-initial">
            <div className="avatar">
              <div className="w-6 rounded-full">
                <img title="User profile photo" src={user?.imageUrl} />
              </div>
            </div>
          </div>

          <div className="flex-1">
            <textarea
              name="content"
              id="trigger-post-content"
              className="appearance-none bg-transparent p-4 w-full rounded-lg "
              placeholder="Write something here..."
            />
          </div>
        </div>
      </div>

      <dialog id="write_post_modal" className="modal">
        <div className="modal-box w-1/2 sm:w-1/2 lg:1/2 max-w-5xl rounded-lg  overflow-hidden">
          <p className="font-bold text-center text-xl pb-4">Write post</p>

          <form action={handleForm}>
            <div className="flex w-full items-center space-x-4 h-14">
              <div className="flex-1 basis-3/4 h-full">
                <input
                  name="title"
                  className="input input-bordered input-secondary h-full w-full px-4"
                  placeholder="Title"
                  required
                />
              </div>
              <div className="flex-1 basis-1/4 h-full">
                <select
                  className="select select-secondary w-full h-full max-w-xs"
                  name="flair"
                >
                  {flairs.map((f) => (
                    <option value={f} key={f}>
                      {f}
                    </option>
                  ))}
                </select>
              </div>
            </div>
            <div className="flex w-full h-96 py-8">
              <textarea
                name="content"
                id="post-content"
                className="appearance-none bg-transparent w-full p-4 rounded-lg border border-white "
                placeholder="Write something here..."
                required
              />
            </div>
            
            <div className="flex mt-8 justify-end text-center">
              <input
                type="submit"
                className="btn btn-outline btn-primary"
                placeholder="Submit"
              />
            </div>
            <input name="author_authId" value={authId} hidden />
          </form>
        </div>
        <form method="dialog" className="modal-backdrop">
          <button>close</button>
        </form>
      </dialog>
    </>
  );
}
