import { Box, Button, Typography, Paper } from "@mui/material";

export default function Page() {
   return (
      <Box
         sx={{
            pt: 8,
            pb: 6,
         }}
      >
         <Typography
            sx={{ fontWeight: "medium" }}
            component="h1"
            variant="h3"
            align="center"
            gutterBottom
         >
            About Me
         </Typography>
         <Paper sx={{ margin: 3 }}>
            <Typography sx={{ padding: 3 }} variant="body1" align="center">
               Hi! I'm Alexis, a 22 year old developer from Dallas, Texas. I
               graduated with a Bachelor of Science in Computer Science from The
               University of Texas at Dallas in May of 2023. My favorite
               programming languages are Java and Python, and I'm currently
               learning how to use JavaScript and TypeScript for web development
               with React and Next.js. I have some experience using HTML and CSS
               as well.
            </Typography>
         </Paper>
         <Typography
            sx={{ fontWeight: "medium" }}
            component="h1"
            variant="h3"
            align="center"
            gutterBottom
         >
            About This Website
         </Typography>
         <Paper sx={{ margin: 3 }}>
            <Typography sx={{ padding: 3 }} variant="body1" align="center">
               This website was built using the Next.js web framework with React
               components and written using TypeScript. The GitHub repository
               for this website can be found on my Projects page, if interested!
            </Typography>
         </Paper>
      </Box>
   );
}
