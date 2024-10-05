import { Footer } from "@/components/Footer";
import Hero from "@/components/Hero";
import Navbar from "@/components/Navbar";
import { Testimonials } from "@/components/Testimonials";
import { BackgroundLines } from "@/components/ui/background-lines";
import HeroBackground from "@/components/ui/custom-grid";
import HeroText from "@/components/ui/hero-text";

export default function Home() {
  return (
    <>
      <Navbar />
      <div className="bg-zinc-50 dark:bg-zinc-950 items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
        <main className="">
          {/* <HeroBackground />
          <BackgroundLines className="flex items-center justify-center flex-col">
            <h2 className="bg-clip-text text-transparent text-center bg-gradient-to-b from-zinc-900 to-zinc-700 dark:from-zinc-600 dark:to-white text-2xl md:text-4xl lg:text-7xl font-sans py-2 md:py-10 relative z-20 font-bold tracking-tight mx-auto">
              Intelligent Tutoring System
            </h2>
            <p className="max-w-xl mx-auto text-sm md:text-lg text-zinc-700 dark:text-zinc-400 text-center z-10">Lorem ipsum dolor sit amet consectetur adipisicing elit. Numquam error dolor ipsa hic sint reiciendis inventore reprehenderit optio unde, doloribus excepturi, perferendis eius ad harum enim eum ut architecto. Laborum.</p>
          </BackgroundLines> */}
          <Hero />
          <div className="mt-10">
            <h3 className="font-semibold text-black text-2xl">Hear what our customers have to say</h3>
            <Testimonials />
          </div>
        </main>
      </div>
      <Footer />
    </>
  );
}
