import { getUserBy } from "@/lib/actions/UserService";

export default async function User({ params }: { params: { user: string } }) {
    const user = await getUserBy(params.user);
    return (<h1>{user?.firstName}</h1>);
}