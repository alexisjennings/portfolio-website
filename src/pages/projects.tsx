import { Box, Button, Typography, Paper } from "@mui/material";
import { getProjectURL } from "../utils";

const projects = [
   {
      title: "Text Processing (Python)",
      descr: "This program takes a formatted input file of employees, processes the text, and displays the data in a meaningful way.",
      link: getProjectURL("text_processing"),
   },
   {
      title: "Word Guessing Game (Python)",
      descr: "A word guessing game written in Python",
      link: getProjectURL("word_guessing_game"),
   },
   {
      title: "N-Grams (Python)",
      descr: "A program dealing with N-grams",
      link: getProjectURL("ngrams"),
   },
   {
      title: "Web Crawler (Python)",
      descr: "A program using a web crawler",
      link: getProjectURL("web_crawler"),
   },
   {
      title: "Computer Science II Project 1 (Java)",
      descr: "placeholder",
      link: getProjectURL("Computer_Science_II_Project_1"),
   },
   {
      title: "Computer Science II Project 2 (Java)",
      descr: "placeholder",
      link: getProjectURL("Computer_Science_II_Project_2"),
   },
   {
      title: "Operating Systems Project (Java)",
      descr: "placeholder",
      link: getProjectURL("Operating_Systems_Project"),
   },
];

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
            Projects
         </Typography>
         {projects.map((project) => (
            <>
               <Paper sx={{ margin: 3 }}>
                  <Typography
                     sx={{ fontWeight: "medium", px: 3, pt: 2 }}
                     component="h2"
                     variant="h5"
                     align="center"
                  >
                     {project.title}
                  </Typography>
                  <Typography
                     sx={{ px: 3, pt: 2 }}
                     variant="body1"
                     align="center"
                  >
                     {project.descr}
                  </Typography>
                  <Box sx={{ px: 3, py: 2 }} className="flex justify-center">
                     <Button
                        variant="outlined"
                        href={project.link}
                        target="_blank"
                        rel="noopener noreferrer"
                     >
                        Open in GitHub
                     </Button>
                  </Box>
               </Paper>
            </>
         ))}
      </Box>
   );
}
