import {
   Box,
   Button,
   Card,
   CardActions,
   CardContent,
   Chip,
   Stack,
   Typography,
} from "@mui/material";
import { getProjectURL } from "../utils";

const projects = [
   {
      id: 1,
      title: "Portfolio Website",
      tools: ["TypeScript", "React", "Next.js", "Material UI"],
      descr: "The website you're viewing right now! I built the site to showcase what I have learned from working with React and TypeScript over the past few months.",
      link: "https://github.com/alexisjennings/portfolio-website",
   },
   {
      id: 2,
      title: "Word Guessing Game",
      tools: ["Python", "NLTK"],
      descr: "Given a list of the 50 most common words in a text excerpt, guess a randomly drawn word one letter at a time. The player starts with 5 points, and gains 1 point for each correctly guessed letter, and loses 1 point for each incorrectly guessed letter. The game is over when the full word is revealed, or if the point total reaches 0. Use ! to quit the game.",
      link: getProjectURL("word_guessing_game"),
   },
   {
      id: 3,
      title: "Language Guesser",
      tools: ["Python", "NLTK"],
      descr: "A program that computes the probability of the given text being in English, French, or Italian using N-grams.",
      link: getProjectURL("language_guesser"),
   },
   {
      id: 4,
      title: "Theater Seat Reservation Project",
      tools: ["Java"],
      descr: "This program's purpose is to simulate a movie theater seat reserving program. More details can be found in the comments of the program itself.",
      link: getProjectURL("theater_seat_reservation"),
   },
   {
      id: 5,
      title: "DVD Rental System Project",
      tools: ["Java"],
      descr: "This program's purpose is to simulate a DVD rental system. More details can be found in the comments of the program itself.",
      link: getProjectURL("dvd_rental_system"),
   },
   {
      id: 6,
      title: "DMV Simulation with Semaphores",
      tools: ["Java"],
      descr: "This program uses semaphores to keep track of customers waiting in various lines at a simulated DMV. Multithreading is used to have the customers and DMV staff act at the same time.",
      link: getProjectURL("dmv_simulation"),
   },
];

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
            Projects
         </Typography>
         {projects.map((project) => (
            <Box sx={{ margin: 3 }} key={project.id}>
               <Card>
                  <CardContent>
                     <Typography
                        sx={{ fontWeight: "medium", mb: 1 }}
                        component="h2"
                        variant="h5"
                        align="center"
                     >
                        {project.title}
                     </Typography>
                     <Box className="flex justify-center" sx={{ mb: 1 }}>
                        <Stack direction="row" spacing={1}>
                           {project.tools.map((tool) => (
                              <Chip
                                 label={tool}
                                 color="primary"
                                 size="small"
                                 className="font-medium"
                                 key={tool}
                              />
                           ))}
                        </Stack>
                     </Box>
                     <Typography variant="body1" align="center">
                        {project.descr}
                     </Typography>
                  </CardContent>
                  <Box className="flex justify-center">
                     <CardActions>
                        <Button
                           sx={{ mb: 2 }}
                           variant="contained"
                           href={project.link}
                           target="_blank"
                           rel="noopener noreferrer"
                        >
                           Open in GitHub
                        </Button>
                     </CardActions>
                  </Box>
               </Card>
            </Box>
         ))}
      </Box>
   );
}
