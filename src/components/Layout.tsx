import {
   AppBar,
   Box,
   Drawer,
   IconButton,
   List,
   ListItem,
   ListItemButton,
   ListItemText,
   Toolbar,
   Typography,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import { useState } from "react";

function Header() {
   const [opened, setOpened] = useState(false);
   const toggleDrawer = (open: boolean) => (event: React.MouseEvent) => {
      setOpened(open);
   };
   return (
      <Box sx={{ display: "flex" }}>
         <AppBar>
            <Toolbar>
               <IconButton
                  onClick={toggleDrawer(true)}
                  size="medium"
                  disableRipple
                  disableFocusRipple
                  sx={{ flexGrow: 1, justifyContent: "left" }}
               >
                  <MenuIcon />
               </IconButton>
               <Typography sx={{ fontSize: "h6.fontSize" }}>
                  alexis jennings.
               </Typography>
               <Drawer
                  anchor="top"
                  open={opened}
                  onClose={toggleDrawer(false)}
                  transitionDuration={80}
               >
                  <List>
                     <ListItem disablePadding>
                        <ListItemButton component="a" href="/">
                           <ListItemText
                              sx={{ fontSize: "h6.fontSize" }}
                              primary="home"
                           ></ListItemText>
                        </ListItemButton>
                     </ListItem>
                     <ListItem disablePadding>
                        <ListItemButton component="a" href="/about">
                           <ListItemText
                              sx={{ fontSize: "h6.fontSize" }}
                              primary="about"
                           ></ListItemText>
                        </ListItemButton>
                     </ListItem>
                     <ListItem disablePadding>
                        <ListItemButton component="a" href="/projects">
                           <ListItemText
                              sx={{ fontSize: "h6.fontSize" }}
                              primary="projects"
                           ></ListItemText>
                        </ListItemButton>
                     </ListItem>
                     <ListItem disablePadding>
                        <ListItemButton component="a" href="/contact">
                           <ListItemText
                              sx={{ fontSize: "h6.fontSize" }}
                              primary="contact"
                           ></ListItemText>
                        </ListItemButton>
                     </ListItem>
                  </List>
               </Drawer>
            </Toolbar>
         </AppBar>
      </Box>
   );
}

export default function Layout({ children }: { children: any }) {
   return (
      <>
         <Header />
         {children}
      </>
   );
}
