"use client";
import { submitPost } from "@/lib/actions/PostService";
import { useUser } from "@clerk/nextjs";
import { redirect } from "next/navigation";

const handleForm = (formData: FormData) => {
  // const res = await submitPost(formData);

  // console.log(res);

  redirect("/");
};

export default function Write() {
  const clerkUser = useUser();
  const user = clerkUser.user;

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
        <div className="modal-box w-11/12 sm:w-3/4 lg:3/4 max-w-5xl rounded-lg overflow-hidden">
          <p className="font-bold text-center text-2xl pb-4">Write post</p>

          <form action={handleForm}>
            <div className="flex w-full items-center space-x-4 h-14">
              <div className="flex-1 basis-3/4 h-full">
                <input
                  name="title"
                  className="appearance-none h-full w-full bg-transparent rounded-lg border border-white 
                px-4"
                  placeholder="Title"
                  required
                />
              </div>
              <div className="flex-1 basis-1/4 h-full"></div>
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
            <div className="flex space-x-4 items-center">
              <label>Tags: </label>
              <input
                placeholder="#java, #typescript, #backend, #postgres"
                className="apearance-none bg-transparent border border-white rounded-md w-full p-2"
                disabled
              />
            </div>
            <div className="flex mt-8 justify-end text-center">
              <input
                type="submit"
                className="btn btn-outline btn-primary"
                placeholder="Submit"
              />
            </div>
            <input name="author_authId" value={user?.id} hidden />
          </form>
        </div>
        <form method="dialog" className="modal-backdrop">
          <button>close</button>
        </form>
      </dialog>
    </>
  );
}
