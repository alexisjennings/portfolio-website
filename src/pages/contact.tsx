import { Box, Typography, Paper } from "@mui/material";
import CopyToClipboardButton from "../components/CopyToClipboardButton";

export default function Page() {
   // add linkedin
   return (
      <Box
         sx={{
            pt: 10,
            pb: 6,
         }}
      >
         <Typography
            sx={{ fontWeight: "medium", pl: 3 }}
            component="h1"
            variant="h4"
            align="left"
            gutterBottom
         >
            contact.
         </Typography>
         <Paper sx={{ padding: 3, margin: 3 }}>
            <Box className="flex justify-left">
               <Typography
                  sx={{ mt: 0 }}
                  component="h2"
                  variant="h6"
                  align="center"
               >
                  email: alexisaj@ymail.com
               </Typography>
               <CopyToClipboardButton textToCopy="alexisaj@ymail.com" />
            </Box>
            <Box className="flex justify-left">
               <Typography
                  sx={{ mt: 0 }}
                  component="h2"
                  variant="h6"
                  align="center"
               >
                  LinkedIn:&nbsp;
               </Typography>
               <Typography
                  sx={{ mt: 0 }}
                  component="a"
                  variant="h6"
                  align="center"
                  href="https://www.linkedin.com/in/alexis-jennings/"
                  target="_blank"
                  rel="noopener noreferrer"
                  className="underline"
               >
                  alexis-jennings
               </Typography>
            </Box>
         </Paper>
      </Box>
   );
}
