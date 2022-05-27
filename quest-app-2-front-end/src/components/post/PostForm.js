import * as React from "react";
import { styled } from "@mui/material/styles";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import Collapse from "@mui/material/Collapse";
import Avatar from "@mui/material/Avatar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import { red } from "@mui/material/colors";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ShareIcon from "@mui/icons-material/Share";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import CommentIcon from "@mui/icons-material/Comment";
import { textAlign } from "@mui/system";
import { useHistory } from "react-router-dom/cjs/react-router-dom.min";
import { Button, InputAdornment, OutlinedInput } from "@mui/material";

const ExpandMore = styled((props) => {
    const { expand, ...other } = props;
    return <IconButton {...other} />;
})(({ theme, expand }) => ({
    marginLeft: "auto",
    transition: theme.transitions.create("transform", {
        duration: theme.transitions.duration.shortest,
    }),
}));

export default function PostForm(props) {
    const history = useHistory();
    const {userId, userName, refreshPosts} = props;
    const [expanded, setExpanded] = React.useState(false);
    const [liked, setLiked] = React.useState(false);
    const [text, setText] = React.useState("");
    const [title, setTitle] = React.useState("");
    const [isSent, setIsSent] = React.useState(false);

    const savePost = () => {
        fetch("/posts/",
        {
            method:"POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: title,
                userId: userId,
                text: text,
                userName: 'kien'
            }),

        })
        .then((res) => res.json())
        .catch((error) => console.log(error))
    }

    const handleExpandClick = () => {
        setExpanded(!expanded);
    };

    const goToUser = (id) => {
        history.push(`/users/${id}`);
    };

    const handleLike = () => {
        setLiked(!liked);
    };

    const handleSubmit =   () => {
        console.log(title +" " + text);
        savePost();
        setIsSent(true);
        setTitle("");
        setText("");
        refreshPosts();
    }

    const handleTitle = (value) => {
        setTitle(value);
        setIsSent(false);
    }

    const handleText = (value) => {
        setText(value);
        setIsSent(false);
    }

    return (
    
                <div style={{paddingTop:'50px'}}>
                    <Card
                        sx={{
                            textAlign: "left",
                        }}
                    >
                        <CardHeader
                            avatar={
                                <Avatar
                                    onClick={() => goToUser(userId)}
                                    sx={{ bgcolor: red[500] }}
                                    aria-label="recipe"
                                >
                                    {userName.charAt(0).toUpperCase()}
                                </Avatar>
                            }
                            action={
                                <IconButton aria-label="settings">
                                    <MoreVertIcon />
                                </IconButton>
                            }
                            title={<OutlinedInput
                                id="outlined-adornment-amount"
                                multiline
                                placeholder="Title"
                                inputProps={{maxLength:25}}
                                fullWidth
                                value={title}
                                onChange={ (i) => handleTitle(i.target.value)}
                            >

                            </OutlinedInput>}
                        />
                        <CardContent>
                            <Typography variant="body2" color="text.secondary">
                            <OutlinedInput
                                id="outlined-adornment-amount"
                                multiline
                                placeholder="Text"
                                inputProps={{maxLength:250}}
                                value={text}
                                fullWidth
                                onChange={(i) => handleText(i.target.value)}
                                endAdornment = {
                                    <InputAdornment
                                    position="end"
                                    >
                                        <Button
                                        variant="contained"
                                        style={{background: 'blue', color: 'white'}}
                                        onClick = {handleSubmit}
                                        >Post</Button>
                                    </InputAdornment>
                                }
                            >

                            </OutlinedInput>
                            </Typography>
                        </CardContent>
                        <CardActions disableSpacing>
                            <IconButton
                                onClick={handleLike}
                                aria-label="add to favorites"
                            >
                                <FavoriteIcon
                                    style={liked? { color: "red" } : null}
                                />
                            </IconButton>
                            <ExpandMore
                                expand={expanded}
                                onClick={handleExpandClick}
                                aria-expanded={expanded}
                                aria-label="show more"
                            >
                                <CommentIcon />
                            </ExpandMore>
                        </CardActions>
                        <Collapse in={expanded} timeout="auto" unmountOnExit>
                            <CardContent></CardContent>
                        </Collapse>
                    </Card>
                </div>
    );
}
