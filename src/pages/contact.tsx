import { Box, Button, Grid, Snackbar, Typography, Paper } from "@mui/material";
import { useState } from "react";
import CopyToClipboardButton from "../components/CopyToClipboardButton";

export default function Page() {
   return (
      <Box
         sx={{
            pt: 10,
            pb: 6,
         }}
      >
         <Typography
            sx={{ fontWeight: "medium" }}
            component="h1"
            variant="h4"
            align="center"
            gutterBottom
         >
            Contact
         </Typography>
         <Paper sx={{ padding: 3, margin: 3 }}>
            <Box className="flex justify-center">
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
         </Paper>
      </Box>
   );
}
