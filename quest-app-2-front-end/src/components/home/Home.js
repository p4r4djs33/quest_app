import React, { useEffect, useState } from "react";
import Post from "../post/Post";
import "./Home.scss";
import Container from "@mui/material/Container";
import { makeStyles } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import Box from "@mui/material/Box";
import PostForm from "../post/PostForm";

function Home() {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [postList, setPostList] = useState([]);

    const refreshPosts = () => {
        fetch("/posts")
        .then((res) => res.json())
        .then(
            (result) => {
                setIsLoaded(true);
                setPostList(result);
            },
            (error) => {
                setIsLoaded(true);
                setError(error);
            }
        );
    }

    useEffect(() => {
        refreshPosts();
    }, [postList]);

    if (error) {
        return <div>Error !!!</div>;
    } else if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (
            <React.Fragment>
                <CssBaseline />
                <Container fixed>
                    <PostForm userId ={2} userName={"ddd"} refreshPosts = {refreshPosts} />
                    {postList.map((post) => (
                        <div key={post.id}>
                            <Post
                                userId={post.userId}
                                userName={post.userName}
                                title={post.title}
                                text={post.text}
                            ></Post>
                        </div>
                    ))}
                </Container>
            </React.Fragment>
        );
    }
}
export default Home;
