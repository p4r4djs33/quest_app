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

const ExpandMore = styled((props) => {
    const { expand, ...other } = props;
    return <IconButton {...other} />;
})(({ theme, expand }) => ({
    marginLeft: "auto",
    transition: theme.transitions.create("transform", {
        duration: theme.transitions.duration.shortest,
    }),
}));

export default function Post(props) {
    const history = useHistory();
    const {userId, userName, title, text} = props;
    const [expanded, setExpanded] = React.useState(false);
    const [liked, setLiked] = React.useState(false);

    const handleExpandClick = () => {
        setExpanded(!expanded);
    };

    const goToUser = (id) => {
        history.push(`/users/${id}`);
    };

    const handleLike = () => {
        setLiked(!liked);
    };

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
                            title={title}
                        />
                        <CardContent>
                            <Typography variant="body2" color="text.secondary">
                                {text}
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
