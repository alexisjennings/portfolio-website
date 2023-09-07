import { Box, Container, Paper, Typography } from "@mui/material";

export default function Page() {
   return (
      <Box
         sx={{
            pt: 10,
            pb: 6,
         }}
      >
         <Container maxWidth="sm">
            <Typography
               sx={{ fontWeight: "medium" }}
               component="h1"
               variant="h3"
               align="center"
               gutterBottom
            >
               Welcome!
            </Typography>
         </Container>
      </Box>
   );
}
